package org.alerts;

import lombok.Getter;
import lombok.Setter;
import org.alerts.entity.Alert;
import org.alerts.service.AlertsService;
import org.alerts.service.PriceAlertTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.alerts.service.PriceAlertTaskFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class Scheduler {
    private final ThreadPoolTaskScheduler taskScheduler;
    @Getter
    private final Map<Long, ScheduledFuture<?>> scheduledTasks;
    private final PriceAlertTaskFactory priceAlertTaskFactory;
    @Setter
    private AlertsService alertsService;

    public Scheduler(PriceAlertTaskFactory priceAlertTaskFactory) {
        this.priceAlertTaskFactory = priceAlertTaskFactory;
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.setPoolSize(5);
        this.taskScheduler.initialize();
        this.scheduledTasks = new ConcurrentHashMap<>();
        this.alertsService = new AlertsService();
    }

    public Alert createAlert(Alert alert) {

        alert = alertsService.createOrUpdate(alert);
        if (scheduledTasks.containsKey(alert.getId())) {
            stopTask(alert.getId());
        }

        PriceAlertTask task = priceAlertTaskFactory.create(alert);
        ScheduledFuture<?> future = taskScheduler.schedule(
                task,
                new CronTrigger(alert.getCron())
        );

        scheduledTasks.put(alert.getId(), future);
        return alert;
    }

    public void stopTask(Long taskId) {
        ScheduledFuture<?> future = scheduledTasks.get(taskId);
        if (future != null) {
            future.cancel(true);
            scheduledTasks.remove(taskId);
        }
    }

}
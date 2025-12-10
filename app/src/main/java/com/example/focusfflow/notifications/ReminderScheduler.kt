package com.example.focusfflow.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.focusfflow.data.model.Task

class ReminderScheduler(private val context: Context) {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    /**
     * Programa una notificación para la tarea
     */
    fun scheduleReminder(task: Task) {
        if (!task.isReminderEnabled) {
            Log.d("ReminderScheduler", "Recordatorio desactivado para tarea ${task.id}")
            return
        }

        val scheduledTime = task.getScheduledTime()

        // No programar si ya pasó el tiempo
        if (scheduledTime <= System.currentTimeMillis()) {
            Log.d("ReminderScheduler", "Tiempo ya pasó para tarea ${task.id}")
            return
        }

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("TASK_ID", task.id)
            putExtra("TASK_TITLE", task.title)
            putExtra("TASK_DESCRIPTION", task.description)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            // Programar alarma exacta
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        scheduledTime,
                        pendingIntent
                    )
                    Log.d("ReminderScheduler", "Alarma programada para ${task.title} a las ${scheduledTime}")
                } else {
                    Log.w("ReminderScheduler", "No se tienen permisos para alarmas exactas")
                    // Programar alarma inexacta como fallback
                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        scheduledTime,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    scheduledTime,
                    pendingIntent
                )
                Log.d("ReminderScheduler", "Alarma programada para ${task.title} a las ${scheduledTime}")
            }
        } catch (e: Exception) {
            Log.e("ReminderScheduler", "Error al programar alarma: ${e.message}")
        }
    }

    /**
     * Cancela la notificación de una tarea
     */
    fun cancelReminder(taskId: String) {
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
        Log.d("ReminderScheduler", "Alarma cancelada para tarea $taskId")
    }
}


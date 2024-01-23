package com.vishalpvijayan.smyttens.utils

import android.content.Context
import android.content.Intent
import com.vishalpvijayan.smyttens.activities.ActivityA
import com.vishalpvijayan.smyttens.activities.ActivityB
import com.vishalpvijayan.smyttens.activities.ActivityC

object ActivityNavigator {

    /*fun navigateToActivity(context: Context, activityType: ActivityType, name: String) {
        val intent = Intent(context, getActivityClass(activityType))
        intent.putExtra("activityName", name)
        context.startActivity(intent)
    }*/

/*    private fun getActivityClass(activityType: ActivityType): Class<out Context> {
        return when (activityType) {
            ActivityType.A -> ActivityA::class.java
            ActivityType.B -> ActivityB::class.java
            ActivityType.C -> ActivityC::class.java
            ActivityType.D -> ActivityA::class.java
            ActivityType.E -> ActivityB::class.java
            ActivityType.F -> ActivityC::class.java
        }
    }*/
}

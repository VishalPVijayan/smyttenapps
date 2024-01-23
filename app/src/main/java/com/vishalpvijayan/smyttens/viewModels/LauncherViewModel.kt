package com.vishalpvijayan.smyttens.viewModels

import android.R
import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val _animationFinished = MutableLiveData<Boolean>()
    val animationFinished: LiveData<Boolean> = _animationFinished
    private var animatorSet: AnimatorSet? = null

    fun initializeSplashScreen(handler: Handler) {
        loadAnimation()
        startAnimation(handler)
    }

    private fun loadAnimation() {
        val animator: Animator = AnimatorInflater.loadAnimator(
            getApplication<Application>(),
            R.animator.fade_in
        )

        if (animator is AnimatorSet) {
            animatorSet = animator
        } else if (animator is ObjectAnimator) {
            animatorSet = AnimatorSet().apply {
                play(animator)
            }
        } else {
            throw IllegalArgumentException("Unsupported animator type")
        }
    }

    private fun startAnimation(handler: Handler) {
        animatorSet?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
            }

            override fun onAnimationEnd(animator: Animator) {
                _animationFinished.value = true
            }

            override fun onAnimationCancel(animator: Animator) {
            }

            override fun onAnimationRepeat(animator: Animator) {
            }
        })
        animatorSet?.start()

        handler.postDelayed({
            _animationFinished.value = true
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY: Long = 8000
    }
}




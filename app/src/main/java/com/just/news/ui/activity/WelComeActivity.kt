package com.just.news.ui.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.animation.AccelerateInterpolator
import com.common.base.CommonBaseActivity
import com.just.news.MainActivity.Companion.startMainActivity
import com.just.news.databinding.ActivityWelcomeBinding
import com.just.news.ui.activity.LoginActivity.Companion.startJUSTLoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelComeActivity :
    CommonBaseActivity<ActivityWelcomeBinding>(ActivityWelcomeBinding::inflate) {
    override fun initView() {
        if (!isTaskRoot) {
            finish()
            return
        }

        val alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f)
        val objectAnimator1 =
            ObjectAnimator.ofPropertyValuesHolder(binding.tvName, alpha, scaleX, scaleY)
        val objectAnimator2 =
            ObjectAnimator.ofPropertyValuesHolder(binding.ivLogo, alpha, scaleX, scaleY)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator1, objectAnimator2)
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.duration = 800
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                startJUSTLoginActivity(this@WelComeActivity)
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animatorSet.start()
    }
}
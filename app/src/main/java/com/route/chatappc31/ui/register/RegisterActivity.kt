package com.route.chatappc31.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityRegisterBinding
import com.route.chatappc31.ui.home.HomeActivity
import com.route.notesapp.Base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun generateViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_register
    }

    override fun openHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}

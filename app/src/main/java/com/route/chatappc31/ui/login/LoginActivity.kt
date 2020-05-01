package com.route.chatappc31.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityLoginBinding
import com.route.chatappc31.ui.home.HomeActivity
import com.route.chatappc31.ui.register.RegisterActivity
import com.route.notesapp.Base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.authUser.observe(this, Observer {
            //start home activity
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        })
        viewDataBinding.vm = viewModel
        viewModel.navigator=this
    }

    override fun generateViewModel(): LoginViewModel {
        return  ViewModelProvider(this).get(LoginViewModel::class.java);

    }

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun openRegister() {
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}

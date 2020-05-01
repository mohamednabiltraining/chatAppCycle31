package com.route.chatappc31.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc31.R
import com.route.chatappc31.databinding.ActivityHomeBinding
import com.route.chatappc31.ui.login.LoginActivity
import com.route.notesapp.Base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator=this
        viewDataBinding.vm =viewModel
    }

    override fun generateViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

    override fun logout() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

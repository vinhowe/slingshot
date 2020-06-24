package vin.howe.slingshot

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import vin.howe.slingshot.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.setDefaultButton.setOnClickListener(View.OnClickListener { onSetDefaultButtonClick() })

    }

    override fun onResume() {
        super.onResume()

        val isDefault = isDefault()
        binding.setDefaultButton.visibility = (if (isDefault) View.GONE else View.VISIBLE)
        binding.congratsText.visibility = (if (isDefault) View.VISIBLE else View.GONE)
    }

    fun onSetDefaultButtonClick() {
        println("does this work")
        // Open defaults activity
        val intent = Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private fun isDefault(): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://"))
        val info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return info?.activityInfo != null && packageName == info.activityInfo.packageName
    }
}

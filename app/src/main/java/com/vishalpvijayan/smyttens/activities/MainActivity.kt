package com.vishalpvijayan.smyttens.activities

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.adapter.ButtonAdapter
import com.vishalpvijayan.smyttens.adapter.ProductAdapter
import com.vishalpvijayan.smyttens.data.ButtonEntity
import com.vishalpvijayan.smyttens.data.ProductEntity
import com.vishalpvijayan.smyttens.databinding.ActivityMainBinding
import com.vishalpvijayan.smyttens.fragment.MessageFragment
import com.vishalpvijayan.smyttens.interfaces.OnBtnClickListener

import com.vishalpvijayan.smyttens.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnBtnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    /*private val _products = MutableLiveData<List<ProductEntity>?>()
    val products: MutableLiveData<List<ProductEntity>?> get() = _products

    private val _buttons = MutableLiveData<List<ButtonEntity>?>()
    val buttons: MutableLiveData<List<ButtonEntity>?> get() = _buttons*/
    var loopCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        GlobalScope.launch(Dispatchers.IO) {
            if(mainViewModel.database.myDao().getCountOfRecord()<=0){
                processJsonData()
            }
        }





        Handler(Looper.getMainLooper()).postDelayed({
            val bottomSheetDialogFragment = MessageFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, MessageFragment::class.java.simpleName)
        }, 8000)

        mainViewModel.loadProductsAndButtons()

        mainViewModel.products.observe(this) {
            val productAdapter = ProductAdapter(mainViewModel.products,mainViewModel.database )
            binding.verticalRecyclerView.adapter = productAdapter
            binding.verticalRecyclerView.layoutManager = LinearLayoutManager(this)
        }

        mainViewModel.buttons.observe(this) {
            val buttonAdapter = ButtonAdapter(mainViewModel.buttons, this)
            binding.activityHolderRV.adapter = buttonAdapter
            binding.activityHolderRV.setHasFixedSize(true)
            binding.activityHolderRV.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    private fun loadJSONFromAsset(fileName: String): String? {
        var json: String
        try {
            val inputStream: InputStream = assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun processJsonData() {
        val jsonData: String? = loadJSONFromAsset("smytten.json")
        try {
            val rootJsonObject = JSONObject(jsonData)
            val contentArray = rootJsonObject.getJSONArray("content")


            for (i in 0 until contentArray.length()) {
                val contentObject = contentArray.getJSONObject(i)
                val type = contentObject.getString("type")
                val dataArray = contentObject.getJSONArray("data")

                when (type) {
                    "PRODUCT" -> processProductData(dataArray)
                    "BUTTON" -> processButtonData(dataArray)
                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun processProductData(productArray: JSONArray) {
        for (i in 0 until productArray.length()) {

            val productObject = productArray.getJSONObject(i)

            val id = productObject.getInt("id")
            val brand = productObject.getString("brand")
            val name = productObject.getString("name")
            val image = productObject.getString("image")
            val batch = i

            val product = ProductEntity(0,loopCount,id, brand, name, image,false, false)
            Log.d(ContentValues.TAG, "processProductData: productData $loopCount,$product ")
            GlobalScope.launch(Dispatchers.IO) {
                mainViewModel.database.myDao().insertProduct(product)
            }

        }
        loopCount++
    }

    fun processButtonData(buttonArray: JSONArray) {
        for (i in 0 until buttonArray.length()) {
            val buttonObject = buttonArray.getJSONObject(i)
            val id = buttonObject.getInt("id")
            val name = buttonObject.getString("name")

            val button = ButtonEntity(0,id, name,false)
            GlobalScope.launch(Dispatchers.IO) {
                mainViewModel.database.myDao().insertButton(button)
            }
        }
    }

    override fun onButtonClicked(button: ButtonEntity, name: String) {
        Log.d("Click", "OnClicked")

        navigateToActivity(name)
    }

    private fun navigateToActivity(name: String) {
        when (name) {
            "Activity A" -> {
                val intent = Intent(this, ActivityA::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)
            }

            "Activity B" -> {

                val intent = Intent(this, ActivityB::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)

            }

            "Activity C" -> {

                val intent = Intent(this, ActivityC::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)

            }

            "Activity D" -> {

                val intent = Intent(this, ActivityA::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)

            }

            "Activity E" -> {

                val intent = Intent(this, ActivityB::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)

            }

            "Activity F" -> {

                val intent = Intent(this, ActivityC::class.java)
                intent.putExtra("activityName", name)
                startActivity(intent)

            }
        }
    }
}


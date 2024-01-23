package com.vishalpvijayan.smyttens.viewModels

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishalpvijayan.smyttens.activities.MainActivity
import com.vishalpvijayan.smyttens.data.ButtonEntity
import com.vishalpvijayan.smyttens.data.ProductEntity
import com.vishalpvijayan.smyttens.database.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(private val database: AppDatabase,@ApplicationContext private val context: Context) :
    ViewModel() {

    var loopCount = 0

    private val _products = MutableLiveData<List<ProductEntity>?>()
    val products: MutableLiveData<List<ProductEntity>?> get() = _products

    private val _buttons = MutableLiveData<List<ButtonEntity>?>()
    val buttons: MutableLiveData<List<ButtonEntity>?> get() = _buttons

    init {
        loadProductsAndButtons()
    }

    private fun loadProductsAndButtons() {
        viewModelScope.launch {
            _products.value = withContext(Dispatchers.IO) {
                val products = database.myDao().getAllProducts()
                if (products.isEmpty()) {
                    processJsonData()
                }
                products
            }

            _buttons.value = withContext(Dispatchers.IO) {
                database.myDao().getAllButtons()
            }
        }
    }

    private fun processJsonData() {
        val jsonData: String? = MainActivity().loadJSONFromAsset("smytten.json")
        try {
            val rootJsonObject = jsonData?.let { JSONObject(it) }
            val contentArray = rootJsonObject?.getJSONArray("content")


            for (i in 0 until (contentArray?.length() ?: 0)) {
                val contentObject = contentArray?.getJSONObject(i)
                val type = contentObject?.getString("type")
                val dataArray = contentObject?.getJSONArray("data")

                when (type) {
                    "PRODUCT" -> dataArray?.let { processProductData(it) }
                    "BUTTON" -> dataArray?.let { processButtonData(it) }
                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun processProductData(productArray: JSONArray) {
        for (i in 0 until productArray.length()) {

            val productObject = productArray.getJSONObject(i)

            val id = productObject.getInt("id")
            val brand = productObject.getString("brand")
            val name = productObject.getString("name")
            val image = productObject.getString("image")
            val batch = i

            val product = ProductEntity(0, loopCount, id, brand, name, image, false, false)
            Log.d(ContentValues.TAG, "processProductData: productData $loopCount,$product ")
            GlobalScope.launch(Dispatchers.IO) {
                database.myDao().insertProduct(product)
            }
        }
        loopCount++
    }

    private fun processButtonData(buttonArray: JSONArray) {
        for (i in 0 until buttonArray.length()) {
            val buttonObject = buttonArray.getJSONObject(i)
            val id = buttonObject.getInt("id")
            val name = buttonObject.getString("name")

            val button = ButtonEntity(0, id, name, false)

            GlobalScope.launch(Dispatchers.IO) {
                database.myDao().insertButton(button)
            }
        }
    }

}
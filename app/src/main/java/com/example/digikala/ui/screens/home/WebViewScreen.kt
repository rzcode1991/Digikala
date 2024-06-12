package com.example.digikala.ui.screens.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.ui.components.MyLoading
import com.example.digikala.viewModel.ZarinpalViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    navController: NavHostController,
    myUrl: String,
    zarinViewModel: ZarinpalViewModel = hiltViewModel()
) {

    val decodedUrl = remember { Uri.decode(myUrl) }

    var webViewActive by remember {
        mutableStateOf(true)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    DisposableEffect(Unit) {
        onDispose {
            webViewActive = false
            Log.e("my_tag", "DisposableEffect onDispose called")
        }
    }

    AndroidView(
        factory = { mContext ->
            WebView(mContext).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        if (!webViewActive) {
                            return true
                        }
                        val mUrl = request?.url?.toString()
                        Log.e("my_tag", "callback url is: $mUrl")
                        if (mUrl != null && mUrl.startsWith("myapp://purchase")) {
                            val uri = Uri.parse(mUrl)
                            val authority = uri.getQueryParameter("Authority")
                            val status = uri.getQueryParameter("Status")
                            if (authority != null) {
                                zarinViewModel.emitAuthorityFromCallback(authority)
                            }
                            if (status != null) {
                                zarinViewModel.changeStatusFromCallback(status)
                            }
                            zarinViewModel.changeShouldUserGoToWebView()
                            navController.popBackStack()
                            return true
                        }
                        return super.shouldOverrideUrlLoading(view, request)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        isLoading = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isLoading = false
                    }

                }
                settings.javaScriptEnabled = true
                settings.userAgentString = System.getProperty("http.agent")
                loadUrl(decodedUrl)
            }
        },
        update = { webView ->
            webView.loadUrl(decodedUrl)
        }
    )

    if (isLoading){
        val config = LocalConfiguration.current
        MyLoading(height = config.screenHeightDp.dp, isDark = !isSystemInDarkTheme())
    }

}
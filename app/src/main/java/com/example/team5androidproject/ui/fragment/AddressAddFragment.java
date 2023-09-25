package com.example.team5androidproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.team5androidproject.R;
import com.example.team5androidproject.databinding.FragmentAddressAddBinding;
import com.example.team5androidproject.dto.Receiver;

public class AddressAddFragment extends Fragment {
    private static final String TAG = "AddressAddFragment";
    private FragmentAddressAddBinding binding;
    private NavController navController;
    private WebView webView;

    class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {
            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);

            Log.i(TAG, "processDATA: " + data.toString());

            String[] addressArray = data.split(",");
            if(addressArray.length == 5){
                String zipCode = addressArray[0].trim();
                String address = addressArray[1].trim();
                String roadAddress = addressArray[2].trim();
                String jibunAddress = addressArray[3].trim();
                String extraAddress;
                if(addressArray[4] != null) {
                    extraAddress = addressArray[4].trim();
                }else {
                    extraAddress = "";
                }

                Receiver receiver = new Receiver();

                receiver.setReceiverAddress(address);
                receiver.setReceiverZip(zipCode);
                receiver.setRoadAddress(roadAddress);
                receiver.setJibunAddress(jibunAddress);
                receiver.setExtraAddress(extraAddress);

                Bundle bundle = new Bundle();
                bundle = getArguments();
                bundle.putSerializable("address", receiver);

                navController.navigate(R.id.dest_order, bundle);
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressAddBinding.inflate(inflater);
        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        webView = binding.webView;

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        webView.loadUrl("http://192.168.0.191:8080/our-twinkling-infinitely/getDaumAddress");

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
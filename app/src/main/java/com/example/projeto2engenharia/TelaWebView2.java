package com.example.projeto2engenharia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaWebView2 extends AppCompatActivity {
    private WebView webview;
    private String stringao;

    @SuppressLint({"MissingInflatedId", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_web_view2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        webview = findViewById(R.id.webview2);
        webview.getSettings().setJavaScriptEnabled(true);
        stringao =
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "body {" +
                        " font-family: Arial;" +
                        " text-align: center;" +
                        " background-color: #f2f2f2;" +
                        " margin : 0;" +
                        "}" +
                        ".box {" +
                        " margin: 20px;" +
                        " padding: 20px;" +
                        " background: white;" +
                        "border-radius:10px;" +
                        "}" +
                        "button {" +
                        "padding: 10px;" +
                        "background: #2f9e41;" +
                        "color: white;" +
                        "border: none;" +
                        "border-radius: 10px;" +
                        "}"+
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"box\">" +
                        "<h2>IF Sul de Minas</h2>" +
                        "<p>HTML dentro do app</p>"+
                        "</div>"+
                        "</body>"+
                        "</html>";
        webview.loadDataWithBaseURL(null, stringao, "text/html", "utf-8", null);



    }
}
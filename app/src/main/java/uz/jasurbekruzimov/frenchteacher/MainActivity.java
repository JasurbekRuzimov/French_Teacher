package uz.jasurbekruzimov.frenchteacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CardView readingCard, writingCard, listeningCard, speakingCard;
    ImageView navMenuIcon;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navMenuIcon = findViewById(R.id.nav_menu_icon);
        navMenuIcon.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        readingCard = findViewById(R.id.reading);
//        writingCard = findViewById(R.id.writing_card);
//        listeningCard = findViewById(R.id.listening_card);
//        speakingCard = findViewById(R.id.speaking_card);

        readingCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });
        speakingCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Speaking.class);
            startActivity(intent);
        });
        writingCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Writing.class);
            startActivity(intent);
        });
        listeningCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WriteComprehension.class);
            startActivity(intent);
        });

    }

}
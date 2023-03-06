package com.example.messageparser.feature.comment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.messageparser.R;
import com.example.messageparser.databinding.ActivityCommentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }
}
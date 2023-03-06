package com.example.messageparser.feature.comment;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.messageparser.databinding.ActivityCommentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommentActivity extends AppCompatActivity {
    private CommentViewModel viewModel;
    private ActivityCommentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);

        binding.analyze.setOnClickListener(v -> {
            viewModel.analyzeComment(binding.input.getText().toString());

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        viewModel.commentResult.observe(this, result -> binding.result.setText(result));
    }
}
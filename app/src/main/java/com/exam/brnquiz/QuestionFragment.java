package com.exam.brnquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    List<String> answers;
    ArrayAdapter<String> adapter;

    Question currentQuestion;

    Integer selected = null;
    BRNViewModel brnViewModel;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        Question question = new Question("What is the capital of Colombia", new String[]{"Medellin", "Madrid", "Paris", "Bogota"}, 3);

        brnViewModel = new ViewModelProvider(getActivity()).get(BRNViewModel.class);

        brnViewModel.getQuestion().observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                currentQuestion = brnViewModel.getQuestion().getValue();
            }
        });

        TextView questionText = view.findViewById(R.id.questionText);
        ListView questionList = view.findViewById(R.id.answerList);
        Button submitButton = view.findViewById(R.id.submitButton);

        questionText.setText(question.getQuestion());
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, question.answerChoices);
        questionList.setAdapter(adapter);


        questionList.setOnItemClickListener((parent, view1, position, id) -> {
            selected = position;
        });

        submitButton.setOnClickListener(v -> {
            if (question.getCorrectAnswer() == selected) {
                Toast.makeText(getContext(), "Yay! + 1 point", Toast.LENGTH_SHORT).show();
                brnViewModel.addPoint();
            }else{
                Toast.makeText(getContext(), "Wrong! Use your BRN(AI)", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
}
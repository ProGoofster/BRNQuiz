package com.exam.brnquiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class BRNViewModel extends ViewModel
{

    private MutableLiveData<Question> question;
    private MutableLiveData<Integer> points;

    public BRNViewModel()
    {
        question = new MutableLiveData<>();
        points = new MutableLiveData<>();
        points.setValue(0);
    }

    public void addPoint(){
        points.setValue(points.getValue() + 1);
    }

    public void setQuestion(Question q){
        question.setValue(q);
    }

    public MutableLiveData<Question> getQuestion(){
        return question;
    }

    public MutableLiveData<Integer> getPoints(){
        return points;
    }

}

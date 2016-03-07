package com.formakidov.sparecalendar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.adapter.AdapterAutoComplete;
import com.formakidov.sparecalendar.db.repository.ConsumablesRepository;
import com.formakidov.sparecalendar.model.Consumable;
import com.formakidov.sparecalendar.tools.Formatter;
import com.formakidov.sparecalendar.tools.UiUtils;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddConsumableFragment extends BaseFragment {

    @Bind(R.id.title_name)
    AutoCompleteTextView editorTitle;
    @Bind(R.id.comment)
    TextView comment;

    private Consumable consumable;

    public AddConsumableFragment() {
    }

    public static AddConsumableFragment newInstance() {
        return new AddConsumableFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_consumable, container, false);
        ButterKnife.bind(this, v);
        setupViews(v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        editorTitle.dismissDropDown();
    }

    @Override
    protected void setupViews(View v) {

        long consumableId = getActivity().getIntent().getLongExtra(Constants.EXTRA_CONSUMABLE_ID, 0);
        ConsumablesRepository repo = new ConsumablesRepository(getContext());
        try {
            consumable = repo.findById(consumableId);
        } catch (SQLException e) {
            // TODO: 07.03.2016
            e.printStackTrace();
        }

        LinearLayout title = (LinearLayout) v.findViewById(R.id.title_layout);
        editorTitle = UiUtils.getTitleEditor(title, consumable.getName(), R.string.hint_consumable_name);

        AdapterAutoComplete adapter = new AdapterAutoComplete(getActivity(), R.layout.item_dropdown, getConsumablesFromReferenceBook());
        editorTitle.setAdapter(adapter);

        updateComment();
    }

    public boolean checkData() {
        return true;
    }

    private ArrayList<String> getConsumablesFromReferenceBook() {
//        ArrayList<String> listExercises = new ArrayList<>();
//        ApplicationGB app = ApplicationGB.getInstance();
//        ReferenceBookStorage refBook = app.getReferenceBook();
//        List<ExerciseRefBook> exercises = refBook.getAllExercises();
//        for (ExerciseRefBook ex : exercises) {
//            listExercises.add(ex.getName());
//        }
//
//        Collections.sort(listExercises);
//
//        return listExercises;
        return null;
    }

    private void updateComment() {
        String comment = Formatter.formatParameterValue(getActivity(), consumable.getComment(), R.string.no_comment);
        this.comment.setText(comment);
    }

}

package org.ccomp.data.adapter;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ccomp.R;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.interfaces.CategoryImportanceChangeListener;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public void setName(String name){
        this.name.setText(name);
    }

    public void setImportance(FeedCategoryImportance importance){
        switch (importance){
            case HIDE:{
                if (importanceGroup.getCheckedRadioButtonId() != R.id.cat_hide_radio)
                    importanceGroup.check(R.id.cat_hide_radio);
                break;
            }
            case SHOW:{
                if (importanceGroup.getCheckedRadioButtonId() != R.id.cat_show_radio)
                    importanceGroup.check(R.id.cat_show_radio);
                break;
            }
            case NOTIFY:{
                if (importanceGroup.getCheckedRadioButtonId() != R.id.cat_alert_radio)
                    importanceGroup.check(R.id.cat_alert_radio);
                break;
            }
        }

    }

    private TextView name;
    private RadioGroup importanceGroup;

    public boolean isListeningForCheckChanges() {
        return isListeningForCheckChanges;
    }

    public void setListeningForCheckChanges(boolean listeningForCheckChanges) {
        isListeningForCheckChanges = listeningForCheckChanges;
    }

    private boolean isListeningForCheckChanges = false;

    public CategoryHolder(@NonNull View itemView, CategoryImportanceChangeListener categoryImportanceChangeListener) {
        super(itemView);

        name = itemView.findViewById(R.id.category_entry_name);
        importanceGroup = itemView.findViewById(R.id.importance_radio_group);


        importanceGroup.setOnCheckedChangeListener(
                (radioGroup, checkedId)-> {

                    if (isListeningForCheckChanges) {
                        switch (checkedId) {
                            case (R.id.cat_hide_radio): {
                                categoryImportanceChangeListener.onChange(getAdapterPosition(), FeedCategoryImportance.HIDE);
                                break;
                            }
                            case (R.id.cat_show_radio): {
                                categoryImportanceChangeListener.onChange(getAdapterPosition(), FeedCategoryImportance.SHOW);
                                break;
                            }
                            case (R.id.cat_alert_radio): {
                                categoryImportanceChangeListener.onChange(getAdapterPosition(), FeedCategoryImportance.NOTIFY);
                                break;
                            }
                        }
                    }
                }
        );

    }

}

package core.recycler_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import core.activities.R;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TextViewAdapter extends RecyclerView.Adapter<TextViewHolder> {
    List<String> _dataset;
    FragmentActivity _activity;

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view_item, parent, false);
        return new TextViewHolder(view, _activity);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int i) {
        holder.view().setText(_dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return _dataset.size();
    }
}

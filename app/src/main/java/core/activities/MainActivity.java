package core.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import core.recycler_view.TextViewAdapter;
import core.services.ModernPhrasesSupplier;
import core.shared.Traceable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MainActivity extends AppCompatActivity implements Traceable {
    @NonFinal
    Supplier<String> modernPhrasesSupplier;
    int phrasesCount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // dataset building
        modernPhrasesSupplier = new ModernPhrasesSupplier(this);
        List<String> dataset = IntStream.range(0, phrasesCount)
                .mapToObj(ignored -> modernPhrasesSupplier.get())
                .collect(Collectors.toList());
        // dataset binding
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new TextViewAdapter(dataset, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.example.amarjot.android_fragments_example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.AbstractCollection;

public class MainActivity extends AppCompatActivity {

    private boolean swap_switch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragments();
        setupButton();
    }

    private void setupButton() {
        final Button swapButton = findViewById(R.id.buttonSwap);
        swapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Create fragment and give it an argument specifying the article it should show
                fragment_a fragmentA = new fragment_a();
                fragment_b fragmentB = new fragment_b();

                FragmentTransaction transactionA = getSupportFragmentManager().beginTransaction();
                FragmentTransaction transactionB = getSupportFragmentManager().beginTransaction();

                // Swap Fragments with each other
                if (swap_switch)
                {
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transactionA.replace(R.id.fragmentLeft, fragmentB);
                    transactionB.replace(R.id.fragmentRight, fragmentA);
                    swap_switch = false;
                }
                else
                {
                    transactionA.replace(R.id.fragmentLeft, fragmentA);
                    transactionB.replace(R.id.fragmentRight, fragmentB);
                    swap_switch = true;
                }

                // Commit the transaction
                transactionA.commit();
                transactionB.commit();
            }
        });
    }

    public void setupFragments()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Creating fragmentA and placing it on left side
        FragmentTransaction fragmentTransactionLeft = fragmentManager.beginTransaction();
        fragment_a fragmentA = new fragment_a();
        fragmentTransactionLeft.add(R.id.fragmentLeft, fragmentA);
        fragmentTransactionLeft.commit();

        // Creating fragmentB and placing it on right side
        FragmentTransaction fragmentTransactionRight = fragmentManager.beginTransaction();
        fragment_b fragmentB = new fragment_b();
        fragmentTransactionRight.add(R.id.fragmentRight, fragmentB);
        fragmentTransactionRight.commit();
    }
}

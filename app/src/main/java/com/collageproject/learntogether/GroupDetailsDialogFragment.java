package com.collageproject.learntogether;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupDetailsDialogFragment extends DialogFragment {

    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    String user=mAuth.getUid();
    String mob =mAuth.getPhoneNumber();
    private DatabaseReference groupsRef;
    public Button createGroupButton; // Updated: Make these variables public
    public Button joinGroupButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_group_details, null);

        groupsRef = FirebaseDatabase.getInstance().getReference().child("Groups");

        EditText groupNameEditText = dialogView.findViewById(R.id.edit_text_group_name);
        Button confirmButton = dialogView.findViewById(R.id.button_confirm);

        confirmButton.setOnClickListener(v -> {
            String groupName = groupNameEditText.getText().toString().trim();
            if (!groupName.isEmpty()) {
                joinExistingGroup(groupName);
                dismiss(); // Close the dialog after the action is performed
            }
        });

        builder.setView(dialogView);
        return builder.create();
    }

    private void joinExistingGroup(String groupName) {
        // Logic to join an existing group in the Firebase database
        // For example, assuming the user's phone number is available in userPhoneNumber variable
        // Add the user to the participants of the group
        groupsRef.child(groupName).child("participants").child(mob).setValue(true);

        // Disabling create and join buttons after joining a group
        //createGroupButton.setVisibility(View.GONE);
        //joinGroupButton.setVisibility(View.GONE);
    }
}

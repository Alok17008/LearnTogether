package com.collageproject.learntogether;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupListFragment extends Fragment {

    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    String user=mAuth.getUid();
    String mob =mAuth.getPhoneNumber();
    private RecyclerView groupRecyclerView;
    private ArrayList<String> groupNamesList;
    private ChatAdapter groupListAdapter;
    private DatabaseReference groupsRef;

    private Button createGroupButton;
    private Button joinGroupButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_list_fragment, container, false);



        groupRecyclerView = view.findViewById(R.id.recycler_view_groups);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        groupNamesList = new ArrayList<>();
        groupListAdapter = new ChatAdapter(groupNamesList);
        groupListAdapter.setOnItemClickListener(this::openChatForGroup); // Set click listener
        groupRecyclerView.setAdapter(groupListAdapter);

        groupsRef = FirebaseDatabase.getInstance().getReference().child("Groups");
        fetchGroupList();

        createGroupButton = view.findViewById(R.id.button_create_group);
        joinGroupButton = view.findViewById(R.id.button_join_group);

        createGroupButton.setOnClickListener(v -> showGroupDetailsDialog("create"));
        joinGroupButton.setOnClickListener(v -> showGroupDetailsDialog("join"));

        return view;
    }

    private void openChatForGroup(int position) {
        String selectedGroupName = groupNamesList.get(position);

        // Navigate to ChatFragment with selected group name
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("groupName", selectedGroupName);
        chatFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, chatFragment) // Replace fragment_container with your actual container ID
                .addToBackStack(null)
                .commit();
    }

    private void fetchGroupList() {
        groupsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupNamesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String groupName = snapshot.getKey();

                    for (DataSnapshot snap:snapshot.getChildren()) {
                        String child=snap.getKey();
                        if (child.equals("participants")){
                            for (DataSnapshot snap2:snap.getChildren()) {
                                String mobileno=snap2.getKey();
                                if (mobileno.equals(mob)){
                                    if (groupName != null) {
                                        groupNamesList.add(groupName);
                                    }
                                }
                            }  
                        }
                    }

                }
                groupListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }

    private void showGroupDetailsDialog(String action) {
        GroupDetailsDialogFragment dialogFragment = new GroupDetailsDialogFragment();
        dialogFragment.createGroupButton = createGroupButton; // Pass reference to buttons
        dialogFragment.joinGroupButton = joinGroupButton;
        dialogFragment.show(getChildFragmentManager(), "GroupDetailsDialogFragment");
    }

}

package oss.fruct.org.fishing.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import oss.fruct.org.fishing.FishInfoActivity;
import oss.fruct.org.fishing.R;
import oss.fruct.org.fishing.fragments.dummy.DummyContent;
import oss.fruct.org.fishing.geoobjects.Fish;
import oss.fruct.org.fishing.geoobjects.Lake;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class FragmentFishList extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Lake lake;
    ArrayList<FishListItem> fish;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FishListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    public static FragmentFishList newInstance(String param1, String param2) {
        FragmentFishList fragment = new FragmentFishList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentFishList() {
    }

    public void setLake(Lake lake){
        this.lake = lake;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        fish = new ArrayList<FishListItem>();
        if(lake!=null && lake.getFishesInfo()!= null){
            for(int i =0; i< lake.getFishesInfo().size(); i++){
                fish.add(new FishListItem(((Fish) lake.getFishesInfo().get(i)).getName()));
            }
        }
        adapter = new FishListAdapter(getActivity(),fish);

        setListAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Fish selectedFish = (Fish)lake.getFishesInfo().get(position);
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(((Fish) lake.getFishesInfo().get(position)).getId());
        }
        Intent intent = new Intent(getActivity(), FishInfoActivity.class).putExtra("selectedFish", selectedFish);
        startActivity(intent);
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}

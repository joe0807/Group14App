package au.edu.uts.redylog.redylog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.edu.uts.redylog.redylog.DataManagers.UserManager;
import au.edu.uts.redylog.redylog.Helpers.HelperMethods;
import au.edu.uts.redylog.redylog.Models.User;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText _etFirstName;
    private EditText _etSurname;
    private EditText _etEmail;
    private EditText _etPassword;
    private Button _btnRegister;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        _etFirstName = view.findViewById(R.id.et_register_firstname);
        _etSurname = view.findViewById(R.id.et_register_surname);
        _etEmail = view.findViewById(R.id.et_register_email);
        _etPassword = view.findViewById(R.id.et_register_password);
        _btnRegister = view.findViewById(R.id.btn_register_confirm);

        _btnRegister.setOnClickListener(this);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view == _btnRegister) {
            boolean hasError = false;

            if (!HelperMethods.validName(_etFirstName.getText().toString())) {
                _etFirstName.setError("Please enter a valid first name.");
                hasError = true;
            }

            if (!HelperMethods.validName(_etSurname.getText().toString())) {
                _etSurname.setError("Please enter a valid surname.");
                hasError = true;
            }

            if (!HelperMethods.validEmail(_etEmail.getText().toString())) {
                _etEmail.setError("Please enter a valid email.");
                hasError = true;
            }

            if (!HelperMethods.validPassword(_etPassword.getText().toString())) {
                _etPassword.setError("Please enter a valid password.");
                hasError = true;
            }

            if (!hasError){
                User user = new User(
                        _etFirstName.getText().toString(),
                        _etSurname.getText().toString(),
                        _etEmail.getText().toString(),
                        _etPassword.getText().toString()
                );
                UserManager.getInstance().addUser(user);
                Toast.makeText(getContext(), "Registration successful.", Toast.LENGTH_SHORT).show();
            }
        }
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
        void onFragmentInteraction(Uri uri);
    }
}

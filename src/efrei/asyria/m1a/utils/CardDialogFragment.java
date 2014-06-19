package efrei.asyria.m1a.utils;

import efrei.asyria.m1a.smartcard.HomeActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class CardDialogFragment extends DialogFragment {
	private String id;
	private HomeActivity homeActivity;
	
    public void setId(String id) {
		this.id = id;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Souhaitez-vous effacer cette carte ?")
               .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int index) {
                	   homeActivity.deleteCardToUser(id);
                   }
               })
               .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int index) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }

	public void setHomeActivity(HomeActivity homeActivity) {
		this.homeActivity = homeActivity;
	}
}

package mhci.teamsix.ugs.incampus.util;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Toast;

import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 6/3/2017.
 */

public class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
    Context context;
    public MyMenuItemClickListener(){

    }
    public MyMenuItemClickListener(Context context){
        this.context = context;
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_favourite:
                Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return false;
    }
}

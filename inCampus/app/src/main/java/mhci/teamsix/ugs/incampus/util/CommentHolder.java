package mhci.teamsix.ugs.incampus.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 7/3/2017.
 */

public class CommentHolder extends RecyclerView.ViewHolder{
    public TextView _username, _timestamp, _comments;
    public RatingBar _ratings;
    public CircleImageView _circleImageView;
    public CommentHolder(View itemView) {
        super(itemView);
        _username = (TextView) itemView.findViewById(R.id.comment_username_test);
        _timestamp = (TextView) itemView.findViewById(R.id.comment_timestamp_test);
        _comments = (TextView) itemView.findViewById(R.id.comments_test);
        _ratings = (RatingBar) itemView.findViewById(R.id.comment_rating_test);
        _circleImageView = (CircleImageView) itemView.findViewById(R.id.comment_profile_img);
    }
}

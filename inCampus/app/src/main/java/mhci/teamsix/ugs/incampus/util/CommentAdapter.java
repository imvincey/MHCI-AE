package mhci.teamsix.ugs.incampus.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 7/3/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {
    private Context appContext;
    private List<Comment> commentList;

    public CommentAdapter(Context appContext, List<Comment> commentList) {
        this.appContext = appContext;
        this.commentList = commentList;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments, parent, false);
        return new CommentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder._username.setText(comment.getName());
        holder._timestamp.setText(comment.getTimestamp());
        holder._comments.setText(comment.getDescription());
        holder._ratings.setRating(comment.getRatings());

        String imgUrl = "https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2016/06/CmNf5E2WYAATmg_.jpg";

        Glide.with(appContext).load(imgUrl).into(holder._circleImageView);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}

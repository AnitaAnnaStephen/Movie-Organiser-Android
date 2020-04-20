package com.example.movienavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter2 extends ListAdapter<Movie, MovieAdapter2.MovieHolder> {
    private OnItemClickListener listener;
    public MovieAdapter2() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {


        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getSummary().equals(newItem.getSummary()) &&
                    oldItem.getCast().equals(newItem.getCast()) &&
                    oldItem.getLanguage().equals(newItem.getLanguage()) &&
                    oldItem.getLink().equals(newItem.getLink()) &&
                    oldItem.getYear().equals(newItem.getYear()) ;
        }
    };


   /* @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movies, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewYear.setText(currentNote.getYear());


    }*/
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_movies, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie currentNote = getItem(position);

        holder.textViewTitle1.setText(currentNote.getTitle());
        holder.textViewYear1.setText(currentNote.getYear());
        holder.textViewCast.setText(currentNote.getCast());
        holder.textViewSummary.setText(currentNote.getSummary());
        holder.textViewLanguage.setText(currentNote.getLanguage());
        holder.textViewLink.setText(currentNote.getLink());

    }
    /*  @Override
      public int getItemCount() {
          return notes.size();
      }*/
    public Movie getNoteAt(int position) {
        return getItem(position);
    }



    class MovieHolder extends RecyclerView.ViewHolder {
       /* private TextView textViewTitle;
        private TextView textViewYear;*/
        private TextView textViewTitle1;
        private TextView textViewYear1;
        private TextView textViewCast;
        private TextView textViewLanguage;
        private TextView textViewSummary;
        private TextView textViewLink;

        public MovieHolder(View itemView) {
            super(itemView);
           // textViewTitle = itemView.findViewById(R.id.Title);
            ///textViewYear = itemView.findViewById(R.id.Year);
            textViewTitle1 = itemView.findViewById(R.id.txt_Title);
            textViewYear1 = itemView.findViewById(R.id.txt_Year);
            textViewCast=itemView.findViewById(R.id.txt_Cast);
            textViewLanguage=itemView.findViewById(R.id.txt_Language);
            textViewSummary=itemView.findViewById(R.id.txt_Summary);
            textViewLink=itemView.findViewById(R.id.txt_Link);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}

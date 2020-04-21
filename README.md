# Movie-Organiser-Android
Android assignment


<?xml version="1.0" encoding="utf-8"?>
<merge
    xmlns:android="http://schemas.android.com/apk/res/android"

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"

    tools:parentTag="androidx.constraintlayout.widget.ConstraintLayout">

    <androidx.constraintlayout.widget.Placeholder
        android:id="@+id/placeholder_image"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:content="@+id/image"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintDimensionRatio="16:9"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0" />

    <androidx.constraintlayout.widget.Placeholder
        android:id="@+id/placeholder_button"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginEnd="64dp"
        android:layout_marginBottom="8dp"
        app:content="@+id/button"
        app:layout_constraintBottom_toBottomOf="@+id/placeholder_image"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/placeholder_image"
        app:layout_constraintVertical_bias="0.465" />

</merge>

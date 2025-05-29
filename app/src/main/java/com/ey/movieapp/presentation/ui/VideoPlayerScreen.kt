package com.ey.movieapp.presentation.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayerScreen(videoUrl: String) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                }

                Lifecycle.Event.ON_START -> {
                    exoPlayer.playWhenReady = true
                }

                Lifecycle.Event.ON_RESUME -> {
                    // Resume playback or UI updates if needed
                    exoPlayer.playWhenReady = true
                }

                Lifecycle.Event.ON_PAUSE -> {
                    // Pause playback
                    exoPlayer.playWhenReady = false
                }

                Lifecycle.Event.ON_STOP -> {
                    // Release heavy resources or pause if needed
                    exoPlayer.playWhenReady = false
                }

                Lifecycle.Event.ON_DESTROY -> {
                    // Cleanup
                    exoPlayer.release()
                }

                Lifecycle.Event.ON_ANY -> {

                }

            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
            }
        },
        modifier = Modifier
    )
}

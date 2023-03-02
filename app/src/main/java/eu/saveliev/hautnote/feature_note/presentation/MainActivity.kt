package eu.saveliev.hautnote.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import eu.saveliev.hautnote.feature_note.presentation.add_edit_note.AddEditNoteScreen
import eu.saveliev.hautnote.feature_note.presentation.notes.NotesScreen
import eu.saveliev.hautnote.feature_note.presentation.util.Screen
import eu.saveliev.hautnote.ui.theme.HautNoteTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HautNoteTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}&noteIcon={noteIcon}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteIcon"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            val icon = it.arguments?.getInt("noteIcon") ?: 0
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color,
                                //noteIcon = icon
                            )
                        }
                    }
                }
            }
        }
    }
}


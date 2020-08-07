package at.bwappsandmore.whattocook.di

import at.bwappsandmore.whattocook.MainActivity
import at.bwappsandmore.whattocook.ui.viewmodel.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelModule {
    companion object{
        @Provides
        fun providesSharedViewModel (activity: MainActivity) : SharedViewModel = activity.viewModel
    }
}
import { Routes } from '@angular/router';
import { ActivityComponent } from './MyComponents/activity/activity.component';
import { WorkoutComponent } from './MyComponents/workout/workout.component';
import { GoalComponent } from './MyComponents/goal/goal.component';
import { DashboardComponent } from './MyComponents/dashboard/dashboard.component';
import { HomeComponent } from './MyComponents/home/home.component';

export const routes: Routes = [
    { path: '', component: HomeComponent }, // Default Route for Home Page
    { path: "activity", component: ActivityComponent },
    { path: "workout", component: WorkoutComponent },
    { path: "goal", component: GoalComponent },
    { path: "dashboard", component: DashboardComponent },
];

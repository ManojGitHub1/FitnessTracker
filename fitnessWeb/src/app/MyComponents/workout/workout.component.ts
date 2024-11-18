import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-workout',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.scss']
})
export class WorkoutComponent {

  gridstyle = {
    width: '100%',
    textAlign: 'center'
  };

  workoutForm!: FormGroup;
  workouts: any;

  listOfType: any[] = [
    'Cardio',
    'Strength',
    'Endurance',
    'Flexibility',
    'Balance',
    'Stretching',
    'Yoga',
    'Dance',
    'Swimming',
    'Running',
    'Cycling',
    'Jumping',
    'Sprinting',
    'Walking',
    'Boxing',
    'CrossFit',
    'Rowing',
    'Material Arts',
    'Gymnasitcs',
    'Snowboarding',
    'Surfing',
  ];


  // Injecting all below
  constructor( private fb: FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ) {

  }

  ngOnInit() {
    this.workoutForm = this.fb.group({
      type: [null, [Validators.required]],
      caloriesBurned: [null, [Validators.required, Validators.min(0)]],
      duration: [null, [Validators.required, Validators.min(0)]],
      date: [null, [Validators.required]]
    });
    
    this.getAllWorkouts();

  }

  submitForm() {
    const formData = {
      ...this.workoutForm.value,
      date: this.workoutForm.value.date.toISOString().split('T')[0],
    };

    this.userService.postWorkout(formData).subscribe({
      next: () => {
        this.message.success('Workout posted successfully', { nzDuration: 5000 });
        this.workoutForm.reset();
        this.getAllWorkouts();
      },
      error: () => {
        this.message.error('Error while posting Workout', { nzDuration: 4000 });
      }
    });
  }

  getAllWorkouts(){
    this.userService.getWorkout().subscribe({
      next: (res) => {
        this.message.success('Workouts fetched successfully', { nzDuration: 5000 });
        this.workouts = res;
      },
      error: () => {
        this.message.error('Error while getting Workout Data', { nzDuration: 4000 });
      }
    });
  }


}

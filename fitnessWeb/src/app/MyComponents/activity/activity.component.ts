import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-activity',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.scss']
})

export class ActivityComponent {

  gridstyle = {
    width: '100%',
    textAlign: 'center'
  };

  activityForm!: FormGroup;
  activities: any;

  constructor(private fb: FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, [Validators.required, Validators.min(0)]],
      steps: [null, [Validators.required, Validators.min(0)]],
      distance: [null, [Validators.required, Validators.min(0)]],
      date: [null, [Validators.required]]
    });

    this.getAllActivities();
  }

  submitForm() {

    // the ... (spread operator) is necessary in this context
    // if you are modifying the form data while keeping the rest of the data intact.
    const formData = {
      ...this.activityForm.value,
      date: this.activityForm.value.date.toISOString().split('T')[0],  // Formats date to 'yyyy-MM-dd'
    };

    this.userService.postActivity(formData).subscribe({
      next: () => {
        this.message.success('Activity posted successfully', { nzDuration: 5000 });
        this.activityForm.reset();
        this.getAllActivities();
      },
      error: () => {
        this.message.error('Error while posting Activity', { nzDuration: 4000 });
      }
    });
  }

  getAllActivities(){
    this.userService.getActivity().subscribe({
      next: (res) => {
        this.message.success('Activity fetched successfully', { nzDuration: 5000 });
        this.activities = res;
      },
      error: () => {
        this.message.error('Error while getting Activities Data', { nzDuration: 4000 });
      }
    });
  }


}

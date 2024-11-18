import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from '../../service/user.service';
import { SharedModule } from '../../shared/shared.module';

@Component({
  selector: 'app-goal',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './goal.component.html',
  styleUrls: ['./goal.component.scss']
})

export class GoalComponent {

  gridstyle = {
    width: '100%',
    textAlign: 'center'
  };

  goalForm!: FormGroup;
  goals: any;

  constructor(private fb: FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.goalForm = this.fb.group({
      description: [null, [Validators.required]],
      startDate: [null, [Validators.required]],
      endDate: [null, [Validators.required]],
    });

    this.getAllGoals();
  }

  submitForm() {
    const formData = {
      ...this.goalForm.value,
      startDate: this.goalForm.value.startDate.toISOString().split('T')[0],
      endDate: this.goalForm.value.endDate.toISOString().split('T')[0],
    };

    this.userService.postGoal(formData).subscribe({
      next: () => {
        this.message.success('Goal posted successfully', { nzDuration: 5000 });
        this.goalForm.reset();
        this.getAllGoals();
      },
      error: () => {
        this.message.error('Error while posting Goal', { nzDuration: 4000 });
      }
    });
  }

  getAllGoals(){
    this.userService.getGoal().subscribe({
      next: (res) => {
        this.message.success('Goals fetched successfully', { nzDuration: 5000 });
        this.goals = res;
      },
      error: () => {
        this.message.error('Error while getting Goals Data', { nzDuration: 4000 });
      }
    });
  }

  updateStatus(id:number) {
    this.userService.updateGoalStatus(id).subscribe({
      next: (res) => {
        this.message.success('Goals updated successfully', { nzDuration: 5000 });
        this.getAllGoals();
      },
      error: () => {
        this.message.error('Error while updating goals', { nzDuration: 4000 });
      }
    });
  }


}

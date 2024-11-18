import { Component, ElementRef, ViewChild } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { UserService } from '../../service/user.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import Chart, { ChartOptions } from 'chart.js/auto';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DatePipe]
})
export class DashboardComponent {

  gridstyle = {
    width: '100%',
    textAlign: 'center'
  };

  statsData: any = {};

  workouts: any;
  activities: any;

  @ViewChild('workoutLineChart') private workoutLineChartRef:ElementRef;
  @ViewChild('activityLineChart') private activityLineChartRef:ElementRef;

  constructor(
    private userService: UserService,
    private message: NzMessageService,
    // Pipes
    private datePipe:DatePipe
  ) { }

  ngOnInit(){
    this.getStats();
    this.getGraphs();
  }

  getStats() {
    this.userService.getStats().subscribe({
      next: (res) => {
        this.message.success('Stats fetched successfully', { nzDuration: 5000 });
        this.statsData = res;
      },
      error: () => {
        this.message.error('Error while fetching Stats', { nzDuration: 4000 });
      }
    });
  }

  getGraphs() {
    this.userService.getGraphs().subscribe({
      next: (res) => {
        this.message.success('Graphs fetched successfully', { nzDuration: 4000 });
        this.workouts = res.workoutDTO;
        this.activities = res.activityDTO;
        if(this.workoutLineChartRef || this.activityLineChartRef){
          this.createLineChart();
        }
      },
      error: () => {
        this.message.error('Error while fetching Graphs', { nzDuration: 4000 });
      }
    });
  }

  ngAfterViewInit() {
    if(this.workouts && this.activities) {
      this.createLineChart();
    }
  }

  createLineChart() {

    if (!this.workouts || !this.activities) {
      console.error("Data for workouts or activities is not available.");
      return;
    }
    const workoutCtx = this.workoutLineChartRef.nativeElement.getContext('2d');
    const activityCtx = this.activityLineChartRef.nativeElement.getContext('2d');

    new Chart(workoutCtx, {
      type: 'line',
      data: {
        labels: this.workouts.map((data: {date:any;}) => this.datePipe.transform(data.date, 'MM/dd')),
        datasets: [
          {
            label: 'Calories Burned',
            data: this.workouts.map((data: {caloriesBurned: any;}) => data.caloriesBurned),
            fill: false,
            borderWidth: 1,
            backgroundColor: 'rgba(80, 200, 120, 0.6)',
            borderColor: 'rgba(0, 100, 0, 1)'
          },
          {
            label: 'Duration',
            data: this.workouts.map((data: {duration: any;}) => data.duration),
            borderWidth: 1
          },
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    new Chart(activityCtx, {
      type: 'line',
      data: {
        labels: this.activities.map((data: {date:any;}) => this.datePipe.transform(data.date, 'MM/dd')),
        datasets: [
          {
            label: 'Calories Burned',
            data: this.activities.map((data: {caloriesBurned: any;}) => data.caloriesBurned),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(80, 200, 120, 0.6)',
            borderColor: 'rgba(0, 100, 0, 1)'
          },
          {
            label: 'Steps',
            data: this.activities.map((data: {steps: any;}) => data.steps),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(255, 180, 120, 0.6)',
            borderColor: 'rgba(255, 100, 0, 1)'
          },
          {
            label: 'Distance',
            data: this.activities.map((data: {distance: any;}) => data.distance),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(255, 200, 200, 0.6)',
            borderColor: 'rgba(255, 0, 100, 1)'
          },
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

  }


}


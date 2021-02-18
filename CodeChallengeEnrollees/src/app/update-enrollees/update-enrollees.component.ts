import { Component, Input, OnInit } from '@angular/core';
import { Enrollee } from '../enrollee';
import { ActivatedRoute, Router } from '@angular/router';
import { EnrolleeService } from '../enrollee.service';
import { FormGroup } from '@angular/forms';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-enrollees',
  templateUrl: './update-enrollees.component.html',
  styleUrls: ['./update-enrollees.component.css']
})
export class UpdateEnrolleesComponent implements OnInit {
  id: string;
  enrollee: Enrollee;
  enrolleeUpdate: FormGroup;
  status: boolean;
  dob: string;
  
  constructor(private route: ActivatedRoute,private router: Router,
    private enrolleeService: EnrolleeService) { }

  ngOnInit() {
    this.enrollee = new Enrollee();
    this.id = this.route.snapshot.params['id'];
    this.enrolleeService.getEnrollee(this.id)
      .subscribe(data => {
        console.log(data)
        this.enrollee = data;
      }, error => console.log(error));
  }


  updateEnrollee() {
    this.enrolleeService.updateEnrollee(this.id, this.enrollee)
      .subscribe(data => {
        console.log(data);
        this.enrollee = new Enrollee();
        this.gotoList();
        //this.toastr.success('Enrollee updated successful');
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateEnrollee();
  }

  gotoList() {
    this.router.navigate(['/enrollees']);
  }

  resetForm() {
   console.log('inside resetForm....');
   this.enrolleeUpdate.reset();
  }

}

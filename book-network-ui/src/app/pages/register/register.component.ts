import { Component } from '@angular/core';
import { RegistrationRequest } from '../../services/models';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';

@Component({
  selector: 'app-register',
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) { }

  registrationRequest: RegistrationRequest = { email: '', firstname: '', lastname: '', password: '' }
  errorMsg: Array<string> = [];

  register(): void {
    this.errorMsg = [];
    this.authService.register({
      body: this.registrationRequest
    }).subscribe({
      next: (rs) => {
        this.router.navigate(['activate-account']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
      }
    })
  }

  login(): void {
    this.router.navigate(['login']);
  }

}

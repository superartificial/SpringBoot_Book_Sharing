import { Component } from '@angular/core';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = { email: '', password: '' };
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,

  ) { }

  login(): void {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse) => {
        // todo save token
        this.router.navigate(['books']);
      },
      error: (err) => {
        console.log(err);

      }
    })
  }

  register(): void {
    this.router.navigate(['register']);
  }

}

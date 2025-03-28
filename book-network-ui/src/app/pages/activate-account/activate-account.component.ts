import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { CodeInputModule } from 'angular-code-input';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-activate-account',
  imports: [CodeInputModule, NgIf],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {

  message: string = '';
  isOkay: boolean = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) { }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  confirmAccount(token: string) {
    this.authService.confirm({
      token
    }).subscribe(
      {
        next: () => {
          this.message = 'Your account has been successfully activated. \nNow you can proceed to login.';
          this.isOkay = true;
          this.submitted = true;
        },
        error: (err) => {
          this.message = "Your token is either expired or invalid.";
          this.submitted = true;
          this.isOkay = false;
        }
      }
    );
  }

  redirectToLogin() {
    this.router.navigate(["login"]);
  }

}

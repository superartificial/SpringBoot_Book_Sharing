import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {

  ngOnInit(): void {
    // const linkElements = document.querySelectorAll('.nav-link');
    // linkElements.forEach(link => {
    //   if (window.location.href.endsWith(link.getAttribute('href') || '')) {
    //     link.classList.add('active');
    //   }
    // })
  }

  logout() {

  }

}

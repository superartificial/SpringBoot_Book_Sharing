import { HttpInterceptorFn } from '@angular/common/http';

// need to also add in app.config.ts: provideHttpClient(withInterceptors([httpTokenInterceptorInterceptor]))
export const httpTokenInterceptorInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem("token");
  let newRequest = req;
  if (!req.url.includes('/auth')) {
    newRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  return next(newRequest);
};

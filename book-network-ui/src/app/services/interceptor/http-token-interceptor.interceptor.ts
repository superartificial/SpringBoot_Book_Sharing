import { HttpInterceptorFn } from '@angular/common/http';

// need to also add in app.config.ts: provideHttpClient(withInterceptors([httpTokenInterceptorInterceptor]))
export const httpTokenInterceptorInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem("token");
  const newRequest = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  return next(newRequest);
};

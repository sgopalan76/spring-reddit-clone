import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { EnrolleesListComponent } from './enrollees-list/enrollees-list.component';
import { EnrolleesDetailsComponent } from './enrollees-details/enrollees-details.component';
import { UpdateEnrolleesComponent } from './update-enrollees/update-enrollees.component';
import { FormsModule } from '@angular/forms';
import { SearchFilterPipe } from './search-filter.pipe';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    EnrolleesListComponent,
    EnrolleesDetailsComponent,
    UpdateEnrolleesComponent,
    SearchFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { Component, OnInit } from '@angular/core';
import { Poll } from '../poll.models';
import { PollService } from '../poll.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-poll',
  standalone: false,
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit {
  polls:Poll[]=[]
  newPoll:Poll={
    question:'',
    options:[
      {optionText:'',voteCount:0},
      {optionText:'',voteCount:0},
    ]
  }

  constructor(private pollService:PollService){}
  ngOnInit(): void {
    this.getPolls()
  }

  getPolls(){
    this.pollService.getPolls().subscribe(
      {
        next:(data)=>{
          this.polls=data;
        },
        error:(error)=>{
          console.log(error)
        }
      }
    );
  }


  createPoll(){
   this.pollService.createPoll(this.newPoll).subscribe({
    next: data =>{
     this.polls.push(data)
     console.log(data)
    },
    error:data=>{
      console.log(data)
    }
   }) 
   this.resetPoll()
  }
  resetPoll(){
    this.newPoll={
      question:'',
      options:[
        {optionText:'',voteCount:0},
        {optionText:'',voteCount:0},
      ]
    }
  }

  vote(pollId:number,OptionIndex:number){
   this.pollService.vote(pollId,OptionIndex).subscribe({
    next:data=>{
      const poll= this.polls.find(p=>p.id===pollId);
      if(poll){
        poll.options[OptionIndex].voteCount++
      }
    },
    error:error=>{
      console.log(error)
    }
   })
  }

  addOption(){
    this.newPoll.options.push( {optionText:'',voteCount:0})
  }

  trackByIndex(index:number){
    return index
  }
  

}

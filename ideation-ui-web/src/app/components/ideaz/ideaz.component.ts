import { Component, OnInit } from '@angular/core';
import {Todo} from './../../model/Todo'

@Component({
  selector: 'app-ideaz',
  templateUrl: './ideaz.component.html',
  styleUrls: ['./ideaz.component.css']
})
export class IdeazComponent implements OnInit {

todo: Todo[];

inputTodo:string = "";

  constructor() { }

  ngOnInit(): void {
    this.todo=[]
  }
/**
 * 
 * @param id 
 */
  toggleDone (id:number) {
    this.todo.map((value, index) => {
      if (index == id) value.completed = !value.completed;

      return value;
    })
  }
/**
 * 
 * @param id 
 */
  deleteIdea (id:number) {
    this.todo = this.todo.filter((value, index) => index !== id);
  }

  addIdea () {
    this.todo.push({
      content: this.inputTodo,
      completed: false
    });

    this.inputTodo = "";
  }
}

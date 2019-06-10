import { ParentTaskObject } from './parent-task-object';

export class TaskObject {
    public taskId: number;
    public task: string;
    public startDate:string;
    public endDate: string;
    public parentId: number;
    public priority: number;
    public status: number;
    public parentTask: ParentTaskObject;
}

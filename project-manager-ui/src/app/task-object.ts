import { ParentTaskObject } from './parent-task-object';
import {UserObject} from './user-object';
import {ProjectObject} from './project-object';

export class TaskObject {
    public taskId: number;
    public task: string;
    public startDate:string;
    public endDate: string;
    public parentId: number;
    public userId: number;
    public projectId: number;
    public priority: number;
    public status: number;
    public parentTask: ParentTaskObject;
    public user: UserObject;
    public project: ProjectObject;
}

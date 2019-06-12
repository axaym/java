import {UserObject} from './user-object';

export class ProjectObject {

    public projectId: number;
    public project: string;
    public startDate:string;
    public endDate: string;
    public priority: number;
    public status: number;
    public userId: number;
    public user: UserObject;
}
